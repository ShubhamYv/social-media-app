import { api } from "../../Config/api"
import * as actionType from "./message.actionType"

// export const createMessageAction = (reqData) => async (dispatch) => {
//   dispatch({ type: actionType.CREATE_MESSAGE_REQUEST })
//   try {
//     const { data } = await api.post(`/api/messages/chat/${reqData.message.chatId}`, reqData.message);
//     reqData.sendMessageToServer(data)
//     console.log("Create message ---- ", data);
//     dispatch({ type: actionType.CREATE_MESSAGE_SUCCESS, payload: data });
//   } catch (error) {
//     console.error("Error in Create Message------ ", error.response ? error.response.data : error.message);
//     dispatch({ type: actionType.CREATE_MESSAGE_FAILURE, payload: error.message || 'Failed to create message' });
//   }
// }

export const createMessageAction = (reqData) => async (dispatch) => {
  dispatch({ type: actionType.CREATE_MESSAGE_REQUEST });
  try {
    const { data } = await api.post(`/api/messages/chat/${reqData.message.chatId}`, reqData.message);
    reqData.sendMessageToServer(data);
    console.log("Create message ---- ", data);
    dispatch({ type: actionType.CREATE_MESSAGE_SUCCESS, payload: data });
  } catch (error) {
    console.error("Error in Create Message------ ", error);
    if (error.response) {
      // Server error
      console.error("Server error: ", error.response.data);
      dispatch({ type: actionType.CREATE_MESSAGE_FAILURE, payload: error.response.data });
    } else if (error.request) {
      // Network error
      console.error("Network error: ", error.request);
      dispatch({ type: actionType.CREATE_MESSAGE_FAILURE, payload: "Network error occurred" });
    } else {
      // Other errors
      console.error("Other error: ", error.message);
      dispatch({ type: actionType.CREATE_MESSAGE_FAILURE, payload: "An error occurred" });
    }
  }
};


export const createChatAction = (chat) => async (dispatch) => {
  dispatch({ type: actionType.CREATE_CHAT_REQUEST })
  try {
    const { data } = await api.post(`/api/chats`, chat);
    console.log("Create Chat ---- ", data)
    dispatch({ type: actionType.CREATE_CHAT_SUCCESS, payload: data })
  } catch (error) {
    console.log("Error in Create Chat------ ", error)
    dispatch({ type: actionType.CREATE_CHAT_FAILURE, payload: error })
  }
}

export const getAllChatAction = () => async (dispatch) => {
  dispatch({ type: actionType.GET_ALL_CHATS_REQUEST })
  try {
    const { data } = await api.get(`/api/chats`);
    console.log("get all chat ---- ", data)
    dispatch({ type: actionType.GET_ALL_CHATS_SUCCESS, payload: data })
  } catch (error) {
    console.log("Error in get all chat ------ ", error)
    dispatch({ type: actionType.GET_ALL_CHATS_FAILURE, payload: error })
  }
}
