import { Grid } from '@mui/material'
import React from 'react'
import Sidebar from '../Sidebar/Sidebar'
import { Route, Routes, useLocation } from 'react-router-dom'
import MiddlePart from '../MiddlePart/MiddlePart'
import Reels from '../Reels/Reels'
import CreateReelsForm from '../Reels/CreateReelsForm'
import Profile from '../Profile/Profile'
import HomeRight from '../HomeRight/HomeRight'

const HomePage = () => {
  const location = useLocation()

  return (
    <div className='px-20'>
      <Grid container spacing={0}>
        <Grid item xs={0} lg={3}>
          <div className='sticky top-0'>
            <Sidebar />
          </div>
        </Grid>
        <Grid
          item
          className='px-5 flex justify-center'
          xs={12}
          lg={location.pathname === "/" ? 6 : 9}
        >
          <Routes>
            <Route path='/' element={<MiddlePart />} />
            <Route path='/reels' element={<Reels />} />
            <Route path='/create-reels' element={<CreateReelsForm />} />
            <Route path='/profile/:id' element={<Profile />} />
          </Routes>
        </Grid>

        {location.pathname === '/' && <Grid item lg={3} className='relative'>
          <div className='sticky top-0 w-full'>
            <HomeRight />
          </div>
        </Grid>}
      </Grid>
    </div>
  )
}

export default HomePage
