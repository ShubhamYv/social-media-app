import { Route, Routes } from 'react-router-dom';
import './App.css';
import Authentication from './Components/Authentication/Authentication';
import HomePage from './Components/Home/HomePage';
import Message from './Components/Message/Message';
import { useDispatch, useSelector } from 'react-redux';
import { useEffect } from 'react';
import { getProfileAction } from './Redux/Auth/auth.action';
import { ThemeProvider } from '@mui/material';
import { darkTheam } from './theam/darkTheam';

function App() {
  const dispatch = useDispatch()
  const { auth } = useSelector(store => store)
  const jwt = localStorage.getItem("jwt")

  useEffect(() => {
    dispatch(getProfileAction(jwt))
  }, [jwt])

  return (
    <ThemeProvider theme={darkTheam}>
      <Routes>
        <Route path='/*' element={auth?.user ? <HomePage /> : <Authentication />} />
        <Route path='/message' element={<Message />} />
        <Route path='/*' element={<Authentication />} />
      </Routes>

    </ThemeProvider>
  );
}

export default App;
