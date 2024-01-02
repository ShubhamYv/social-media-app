import { Card, Grid } from '@mui/material';
import React from 'react';
import loginBackground from '../../Assets/login-background.webp';
import Login from './Login';
import { Route, Routes } from 'react-router-dom';
import Register from './Register';
// import Register from './Register';

const Authentication = () => {
  const backgroundImageStyle = {
    backgroundImage: `url(${loginBackground})`,
    backgroundSize: 'cover',
    backgroundPosition: 'center',
    height: '100vh',
  };

  return (
    <Grid container>
      {/* Image Grid */}
      <Grid item xs={7} style={backgroundImageStyle}>
      </Grid>

      {/* Login Grid */}
      <Grid item xs={5} container justifyContent="center" alignItems="center">
        <Card className='card p-8'>
          <div className='flex flex-col items-center mb-5 space-y-1'>
            <h1 className='logo text-center'>
              SKY Social
            </h1>
            <p className='text-center text-sm w-[70%]'>
              Connecting Lives, Sharing Stories: Your Social World, Your Way
            </p>
          </div>
          <Routes>
            <Route path='/' element={<Login />} />
            <Route path='/login' element={<Login />} />
            <Route path='/register' element={<Register />} />
          </Routes>
        </Card>
      </Grid>
    </Grid>
  );
};

export default Authentication;
