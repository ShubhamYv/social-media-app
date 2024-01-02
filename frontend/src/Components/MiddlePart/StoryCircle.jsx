import { Avatar } from '@mui/material'
import React from 'react'

const StoryCircle = () => {
  return (
    <div>
      <div className='flex flex-col items-center mr-4 cursor-pointer'>
        <Avatar
          src='https://avatars.githubusercontent.com/u/75082349?v=4'
          sx={{ width: "5rem", height: "5rem" }}
        />
        <p>Shubham...</p>
      </div>
    </div>
  )
}

export default StoryCircle
