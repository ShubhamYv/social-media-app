import { Avatar, Card, CardHeader, IconButton } from '@mui/material'
import React from 'react'
import MoreHorizIcon from '@mui/icons-material/MoreHoriz';
import { useSelector } from 'react-redux';

const UserChatCard = ({ chat }) => {

  const { auth } = useSelector(store => store)

  return (
    <Card>
      <CardHeader
        avatar={
          <Avatar
            sx={{
              height: "3.5rem", width: "3.5rem",
              fontSize: "1.5rem", bgcolor: "#191c29", color: "rgb(88, 199, 250)"
            }}
            src='https://cdn.pixabay.com/photo/2023/08/23/03/33/boxer-8207572_640.jpg'
          />
        }

        action={<IconButton>
          <MoreHorizIcon />
        </IconButton>}

        title={auth.user?.id === chat.users[0]?.id
          ? chat.users[1].firstName + " " + chat.users[1].lastName
          : chat.users[0].firstName + " " + chat.users[0].lastName
        }
        subheader="new message"
      />
    </Card>
  )
}

export default UserChatCard
