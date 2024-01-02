import { Avatar, Card, CardHeader } from '@mui/material'
import React, { useState } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import { searchUser } from '../../Redux/Auth/auth.action'
import { createChatAction } from '../../Redux/Message/message.action'

const SearchUser = () => {

  const [username, setUsername] = useState("")
  const dispatch = useDispatch()
  const { auth } = useSelector(store => store)

  const handleSearchUser = (e) => {
    setUsername(e.target.value)
    console.log("Search User")
    dispatch(searchUser(username))
  }

  const handleClick = (id) => {
    dispatch(createChatAction({ userId: id }))
  }

  return (
    <div>
      <div className="relative py-5">
        <input
          className='bg-transparent border border-[#3b4054] outline-none w-full px-5 py-3 rounded-full'
          type="text"
          placeholder='Search user...'
          onChange={handleSearchUser}
        />
        {username && (
          auth.searchUser.map((item) =>
            <Card key={item.id} className='absolute w-full z-10 top-[4.5rem] cursor-pointer'>
              <CardHeader onClick={() => {
                handleClick(item.id)
                setUsername("")
              }}
                avatar={
                  <Avatar
                    src="https://cdn.pixabay.com/photo/2023/11/11/04/03/christmas-8380345_640.png"
                  />}

                title={item.firstName + " " + item.lastName}
                subheader={`@${item.firstName.toLowerCase() + "_" + item.lastName.toLowerCase()}`}
              />
            </Card>)
        )
        }
      </div>
    </div>
  )
}

export default SearchUser
