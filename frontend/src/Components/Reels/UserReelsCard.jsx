import React from 'react'

const UserReelsCard = () => {
  return (
    <div className='w-[15rem] px-2'>
      <video
        controls
        className='w-full h-full'
        src='https://player.vimeo.com/external/384761655.sd.mp4?s=383ab4dbc773cd0d5ece3af208d8f963368f67e4&profile_id=164&oauth2_token_id=57447761'
      />
    </div>
  )
}

export default UserReelsCard
