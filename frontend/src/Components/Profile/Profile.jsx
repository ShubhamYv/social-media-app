import { Avatar, Box, Button, Card, Tab, Tabs } from '@mui/material';
import React from 'react'
// import { useParams } from 'react-router-dom'
import PostCard from '../Post/PostCard';
import UserReelsCard from '../Reels/UserReelsCard';
import { useSelector } from 'react-redux';
import ProfileModal from './ProfileModal';


const tabs = [
  { value: 'post', name: 'Post' },
  { value: 'reels', name: 'Reels' },
  { value: 'saved', name: 'Saved' },
  { value: 'repost', name: 'Repost' },
]

const posts = [1, 1, 1, 1, 1, 1, 1]
const reels = [1, 1, 1, 1, 1, 1, 1]
const savedPost = [1, 1, 1, 1, 1, 1, 1]
const repost = [1, 1, 1, 1, 1, 1, 1]

const Profile = () => {
  // const { id } = useParams();
  const [value, setValue] = React.useState('post');
  const { auth } = useSelector(store => store)
  const [open, setOpen] = React.useState(false);
  const handleOpenProfileModal = () => setOpen(true);
  const handleClose = () => setOpen(false);

  const handleChange = (event, newValue) => {
    setValue(newValue);
  };

  return (
    <Card className='my-10 w-[70%]'>
      <div className='rounded-md'>
        <div className='h-[15rem]'>
          <img
            className='w-full h-full rounded-t-md'
            src="https://cdn.pixabay.com/photo/2023/11/11/10/18/mountain-8380938_640.jpg"
            alt="profile-background"
          />
        </div>
        <div className='px-5 flex justify-between items-start mt-5 h-[5rem]'>
          <Avatar
            className='transform -translate-y-24'
            sx={{ width: '10rem', height: '10rem' }}
            src='https://avatars.githubusercontent.com/u/75082349?v=4'
          />
          {true
            ? <Button sx={{ borderRadius: '20px' }} variant='outlined'>Edit Profile</Button>
            : <Button sx={{ borderRadius: '20px' }} variant='outlined'>Follow</Button>
          }
        </div>
        <div className='p-5'>
          <div>
            <h1 className='py-1 font-bold text-xl'>
              {auth.user?.firstName + " " + auth.user?.lastName}
            </h1>
            <p>
              @{auth.user?.firstName.toLowerCase()
                + "_" +
                auth.user?.lastName.toLowerCase()}
            </p>
          </div>
          <div className='flex gap-5 items-center py-3'>
            <span>41 posts</span>
            <span>35 followers</span>
            <span>20 following</span>
          </div>
          <div>
            <p>Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
          </div>
        </div>

        <section >
          <Box sx={{ width: '100%', borderBottom: 1, borderColor: 'divider' }}>
            <Tabs
              value={value}
              onChange={handleChange}
              aria-label="wrapped label tabs example"
            >
              {tabs.map((item, key) => <Tab value={item.value} label={item.name} wrapped />)}
            </Tabs>
          </Box>

          <div className='flex justify-center'>
            {value === 'post' ? (
              <div className='space-y-5 w-[70%] my-10'>
                {posts.map((item, key) =>
                  <div className='border rounded-md border-slate-100'>
                    <PostCard />
                  </div>)}
              </div>)
              : value === 'reels' ? (
                <div className='flex flex-wrap justify-center my-10 gap-2'>
                  {reels.map((item, key) => <UserReelsCard />)}
                </div>
              ) : value === 'saved' ? (
                <div className='space-y-5 w-[70%] my-10'>
                  {posts.map((item, key) =>
                    <div className='border rounded-md border-slate-100'>
                      <PostCard />
                    </div>)}
                </div>
              ) : ""}
          </div>
        </section>
      </div>
      <section>
        <ProfileModal open={open} handleClose={handleClose} />
      </section>
    </Card>
  )
}

export default Profile
