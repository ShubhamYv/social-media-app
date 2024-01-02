import * as React from 'react';
import CardHeader from '@mui/material/CardHeader';
import Avatar from '@mui/material/Avatar';
import { red } from '@mui/material/colors';
import { Button } from '@mui/material';

const PopularUser = () => {
  return (
    <div>
      <CardHeader
        avatar={
          <Avatar
            src='https://avatars.githubusercontent.com/u/75082349?v=4'
            sx={{ bgcolor: red[500] }} aria-label="recipe"
          />
        }
        action={
          <Button size='small'>Follow</Button>
        }
        title="Shubham Yadav"
        subheader="@ShubhamYv"
      />
    </div>
  )
}

export default PopularUser
