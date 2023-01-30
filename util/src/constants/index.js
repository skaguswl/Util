import { useSelector, useDispatch } from 'react-redux'
import { userAuthSliceActions } from '../redux/userAuthSlice'

import React, {useEffect} from 'react'

export const Token = () => {
    const userAuth = useSelector(state => state.userAuthSlice.userAuth)
    const token = `Bearer ${userAuth.token}`
    return token
}


export const API_BASE_URL = 'http://i8d210.p.ssafy.io:8081';
export const ACCESS_TOKEN = 'accessToken';

export const OAUTH2_REDIRECT_URI = 'http://localhost:3000/oauth2/redirect'

export const GOOGLE_AUTH_URL = API_BASE_URL + '/oauth2/authorize/google?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const FACEBOOK_AUTH_URL = API_BASE_URL + '/oauth2/authorize/kakao?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const KAKAO_AUTH_URL = API_BASE_URL + '/oauth2/authorize/kakao?redirect_uri=' + OAUTH2_REDIRECT_URI;
export const GITHUB_AUTH_URL = API_BASE_URL + '/oauth2/authorize/github?redirect_uri=' + OAUTH2_REDIRECT_URI;