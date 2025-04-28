package com.erp.jwt.service;

import com.erp.jwt.config.jwtConfig.JwtTokenGenerator;
import com.erp.jwt.constants.TokenType;
import com.erp.jwt.entity.RefreshTokenEntity;
import com.erp.jwt.entity.UserInfoEntity;
import com.erp.jwt.mapper.UserInfoMapper;
import com.erp.jwt.records.UserRegistrationDto;
import com.erp.jwt.repo.RefreshTokenRepo;
import com.erp.jwt.repo.UserInfoRepo;
import com.erp.jwt.request.kanban.BoardRequest;
import com.erp.jwt.response.AuthResponse;
import com.erp.jwt.response.kanban.BoardResponse;
import com.erp.jwt.service.kanban.service.BoardService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Optional;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService{

    private final UserInfoRepo userInfoRepo;
    private final RefreshTokenRepo refreshTokenRepo;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final UserInfoMapper userInfoMapper;
    private final BoardService boardService;


    public AuthServiceImpl(UserInfoRepo userInfoRepo, RefreshTokenRepo refreshTokenRepo, JwtTokenGenerator jwtTokenGenerator, UserInfoMapper userInfoMapper, BoardService boardService) {
        this.userInfoRepo = userInfoRepo;
        this.refreshTokenRepo = refreshTokenRepo;
        this.jwtTokenGenerator = jwtTokenGenerator;
        this.userInfoMapper = userInfoMapper;
        this.boardService = boardService;
    }

    @Override
    public AuthResponse getJwtTokenAfterAuthentication(Authentication authentication, HttpServletResponse response) {
        try
        {
            var userInfoEntity = userInfoRepo.findByEmailId(authentication.getName())
                    .orElseThrow(()->{
                        log.error("[AuthService:userSignInAuth] User :{} not found",authentication.getName());
                        return new ResponseStatusException(HttpStatus.NOT_FOUND,"USER NOT FOUND ");});


            String accessToken = jwtTokenGenerator.generateAccessToken(authentication);
            String refreshToken = jwtTokenGenerator.generateRefreshToken(authentication);
            saveUserRefreshToken(userInfoEntity,refreshToken);

            creatRefreshTokenCookie(response,refreshToken);

//            BoardResponse boardResponse = boardService.getBoardByUserId(userInfoEntity.getId());

            log.info("[AuthService:userSignInAuth] Access token for user:{}, has been generated",userInfoEntity.getUserName());
            return  AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .accessTokenExpiry(60 * 60)
                    .userName(userInfoEntity.getUserName())
                    .tokenType(TokenType.Bearer)
                    .userId(userInfoEntity.getId())
                    .build();


        }catch (Exception e){
            log.error("[AuthService:userSignInAuth]Exception while authenticating the user due to :"+e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Please Try Again");
        }
    }

    @Override
    public Object getAccessTokenUsingRefreshToken(String authorizationHeader) {
        if(!authorizationHeader.startsWith(TokenType.Bearer.name())){
            return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Please verify your token type");
        }

        final String refreshToken = authorizationHeader.substring(7);

        //Find refreshToken from database and should not be revoked : Same thing can be done through filter.
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepo.findByRefreshToken(refreshToken)
                .filter(tokens-> !tokens.isRevoked())
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Refresh token revoked"));

        UserInfoEntity userInfoEntity = refreshTokenEntity.getUser();

        //Now create the Authentication object
        Authentication authentication =  createAuthenticationObject(userInfoEntity);

        //Use the authentication object to generate new accessToken as the Authentication object that we will have may not contain correct role.
        String accessToken = jwtTokenGenerator.generateAccessToken(authentication);

        return  AuthResponse.builder()
                .accessToken(accessToken)
                .accessTokenExpiry(5 * 60)
                .userName(userInfoEntity.getUserName())
                .tokenType(TokenType.Bearer)
                .build();
    }

    @Override
    public AuthResponse registerUser(UserRegistrationDto userRegistrationDto, HttpServletResponse httpServletResponse) {
        try{
            log.info("[AuthService:registerUser]User Registration Started with :::{}",userRegistrationDto);

            Optional<UserInfoEntity> user = userInfoRepo.findByEmailId(userRegistrationDto.getUserEmail());
            if(user.isPresent()){
                throw new Exception("User Already Exist");
            }

            UserInfoEntity userDetailsEntity = userInfoMapper.convertToEntity(userRegistrationDto);
            Authentication authentication = createAuthenticationObject(userDetailsEntity);


            // Generate a JWT token
            String accessToken = jwtTokenGenerator.generateAccessToken(authentication);
            String refreshToken = jwtTokenGenerator.generateRefreshToken(authentication);

            UserInfoEntity savedUserDetails = userInfoRepo.save(userDetailsEntity);
            saveUserRefreshToken(userDetailsEntity,refreshToken);
            creatRefreshTokenCookie(httpServletResponse,refreshToken);

//            BoardResponse boardResponse = createDefaultBoardForUser(userRegistrationDto, savedUserDetails);

            log.info("[AuthService:registerUser] User:{} Successfully registered",savedUserDetails.getUserName());
            return   AuthResponse.builder()
                    .accessToken(accessToken)
                    .refreshToken(refreshToken)
                    .accessTokenExpiry(60 * 60)
                    .userName(savedUserDetails.getUserName())
                    .tokenType(TokenType.Bearer)
                    .userId(savedUserDetails.getId())
                    .build();


        }catch (Exception e){
            log.error("[AuthService:registerUser]Exception while registering the user due to :"+e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }

    }

    private BoardResponse createDefaultBoardForUser(UserRegistrationDto userRegistrationDto, UserInfoEntity user) {

        BoardRequest boardRequest = new BoardRequest();
        boardRequest.setBoardName(userRegistrationDto.getUserName());
        boardRequest.setUserId(user.getId());
        log.info("creating default board for user: {}",boardRequest);
        return boardService.createBoard(boardRequest);
    }

    private Authentication createAuthenticationObject(UserInfoEntity userInfoEntity) {
        // Extract user details from UserDetailsEntity
        String username = userInfoEntity.getEmailId();
        String password = userInfoEntity.getPassword();
        String roles = userInfoEntity.getRoles();

        // Extract authorities from roles (comma-separated)
        String[] roleArray = roles.split(",");
        GrantedAuthority[] authorities = Arrays.stream(roleArray)
                .map(role -> (GrantedAuthority) role::trim)
                .toArray(GrantedAuthority[]::new);

        return new UsernamePasswordAuthenticationToken(username, password, Arrays.asList(authorities));

    }

    private void creatRefreshTokenCookie(HttpServletResponse response, String refreshToken) {
        Cookie refreshTokenCookie = new Cookie("refresh_token",refreshToken);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true);
        refreshTokenCookie.setMaxAge(15 * 24 * 60 * 60 ); // in seconds
        response.addCookie(refreshTokenCookie);
    }

    private void saveUserRefreshToken(UserInfoEntity userInfoEntity, String refreshToken) {
        var refreshTokenEntity = RefreshTokenEntity.builder()
                .user(userInfoEntity)
                .refreshToken(refreshToken)
                .revoked(false)
                .build();
        refreshTokenRepo.save(refreshTokenEntity);
    }
}
