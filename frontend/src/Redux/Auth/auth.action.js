import axios from "axios"
import { API_BASE_URL, api } from "../../Config/api"
import { GET_PROFILE_FAILURE, GET_PROFILE_REQUEST, GET_PROFILE_SUCCESS, LOGIN_FAILURE, LOGIN_REQUEST, LOGIN_SUCCESS, LOGOUT, REGISTER_FAILURE, REGISTER_REQUEST, REGISTER_SUCCESS, SEARCH_USER_FAILURE, SEARCH_USER_REQUEST, SEARCH_USER_SUCCESS, UPDATE_PROFILE_FAILURE, UPDATE_PROFILE_REQUEST, UPDATE_PROFILE_SUCCESS } from "./auth.actionType"

export const loginUserAction = (loginData) => async (dispatch) => {
  dispatch({ type: LOGIN_REQUEST })
  try {
    const { data } = await axios.post(`${API_BASE_URL}/auth/signin`, loginData.data)
    if (data.token) {
      localStorage.setItem("jwt", data.token)
    }
    console.log("Login Success ---- ", data)
    dispatch({ type: LOGIN_SUCCESS, payload: data.jwt })
  } catch (error) {
    console.log("Error in loginUserAction ---- ", error)
    dispatch({ type: LOGIN_FAILURE, payload: error })
  }
}


export const registerUserAction = (loginData) => async (dispatch) => {
  dispatch({ type: REGISTER_REQUEST })
  try {
    const { data } = await axios.post(`${API_BASE_URL}/auth/signup`, loginData.data)
    if (data.token) {
      localStorage.setItem("jwt", data.token)
    }
    console.log("Register Success ---- ", data)
    dispatch({ type: REGISTER_SUCCESS, payload: data.jwt })
  } catch (error) {
    console.log("Error in registerUserAction ---- ", error)
    dispatch({ type: REGISTER_FAILURE, payload: error })
  }
}


// PROFILE
export const getProfileAction = (jwt) => async (dispatch) => {
  dispatch({ type: GET_PROFILE_REQUEST })
  try {
    const { data } = await axios.get(`${API_BASE_URL}/api/users/profile`, {
      headers: {
        "Authorization": `Bearer ${jwt}`
      }
    })
    dispatch({ type: GET_PROFILE_SUCCESS, payload: data })
  } catch (error) {
    console.log("Error in getProfileAction ---- ", error)
    dispatch({ type: GET_PROFILE_FAILURE, payload: error })
  }
}


export const updateProfileAction = (reqData) => async (dispatch) => {
  dispatch({ type: UPDATE_PROFILE_REQUEST })
  try {
    const { data } = await api.put(`${API_BASE_URL}/api/users`, reqData)
    console.log("Update Profile Success ---- ", data)
    dispatch({ type: UPDATE_PROFILE_SUCCESS, payload: data })
  } catch (error) {
    console.log("Error in updateProfileAction ---- ", error)
    dispatch({ type: UPDATE_PROFILE_FAILURE, payload: error })
  }
}


// User
export const searchUser = (query) => async (dispatch) => {
  dispatch({ type: SEARCH_USER_REQUEST })
  try {
    const { data } = await api.get(`/api/users/search?query=${query}`)
    console.log("Search User ---- ", data)
    dispatch({ type: SEARCH_USER_SUCCESS, payload: data })
  } catch (error) {
    console.log("Error in Search User ---- ", error)
    dispatch({ type: SEARCH_USER_FAILURE, payload: error })
  }
}



export const logOut = (jwt) => async (dispatch) => {
  localStorage.removeItem("jwt")
  dispatch({ type: LOGOUT, payload: null })
}