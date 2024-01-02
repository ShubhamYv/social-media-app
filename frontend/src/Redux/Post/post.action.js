import { api } from "../../Config/api"
import { CREATE_COMMENT_FAILURE, CREATE_COMMENT_REQUEST, CREATE_COMMENT_SUCCESS, CREATE_POST_FAILURE, CREATE_POST_REQUEST, CREATE_POST_SUCCESS, GET_ALL_POST_FAILURE, GET_ALL_POST_REQUEST, GET_ALL_POST_SUCCESS, GET_USERS_POST_FAILURE, GET_USERS_POST_REQUEST, GET_USERS_POST_SUCCESS, LIKE_POST_FAILURE, LIKE_POST_REQUEST, LIKE_POST_SUCCESS } from "./post.actionType"


export const createPostAction = (postData) => async (dispatch) => {
  dispatch({ type: CREATE_POST_REQUEST })
  try {
    const { data } = await api.post("/api/posts", postData);
    dispatch({ type: CREATE_POST_SUCCESS, payload: data })
  } catch (error) {
    dispatch({ type: CREATE_POST_FAILURE, payload: error })
    console.log("Error in createPostAction ---- ", error)
  }
}

export const getAllPostAction = () => async (dispatch) => {
  dispatch({ type: GET_ALL_POST_REQUEST })
  try {
    const { data } = await api.get("/api/posts");
    dispatch({ type: GET_ALL_POST_SUCCESS, payload: data })
  } catch (error) {
    dispatch({ type: GET_ALL_POST_FAILURE, payload: error })
    console.log("Error in getAllPostAction ---- ", error)
  }
}

export const getUsersPostAction = (userId) => async (dispatch) => {
  dispatch({ type: GET_USERS_POST_REQUEST })
  try {
    const { data } = await api.get(`/api/posts/user/${userId}`);
    dispatch({ type: GET_USERS_POST_SUCCESS, payload: data })
    console.log("getUsersPostAction success --- ", data)
  } catch (error) {
    dispatch({ type: GET_USERS_POST_FAILURE, payload: error })
    console.log("Error in getUsersPostAction ---- ", error)
  }
}

export const likePostAction = (postId) => async (dispatch) => {
  dispatch({ type: LIKE_POST_REQUEST })
  try {
    const { data } = await api.put(`/api/posts/like/${postId}`);
    dispatch({ type: LIKE_POST_SUCCESS, payload: data })
  } catch (error) {
    dispatch({ type: LIKE_POST_FAILURE, payload: error })
    console.log("Error in likePostAction ---- ", error)
  }
}


// Comments
export const createCommentAction = (requestData) => async (dispatch) => {
  dispatch({ type: CREATE_COMMENT_REQUEST });
  try {
    const { data } = await api.post(`/api/comments/post/${requestData.postId}`, requestData.data);
    console.log("createCommentAction success --- ", data);
    dispatch({ type: CREATE_COMMENT_SUCCESS, payload: data });
  } catch (error) {
    console.log("Error in createCommentAction---", error);
    dispatch({ type: CREATE_COMMENT_FAILURE, payload: error });
  }
};

