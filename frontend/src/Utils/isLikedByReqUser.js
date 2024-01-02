export function isLikedByReqUser(userId, item) {
  if (item && item.likes) {
    const likedItem = item.likes.find(like => like.user === userId);
    return likedItem ? likedItem.liked : false;
  }
  return false;
}
