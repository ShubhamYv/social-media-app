import { TextField, Button, Grid } from '@mui/material';
import { ErrorMessage, Field, Form, Formik } from 'formik';
import React from 'react';
import { useDispatch } from 'react-redux';
import * as Yup from 'yup';
import { loginUserAction } from '../../Redux/Auth/auth.action';
import { useNavigate } from 'react-router-dom';

const initialValues = { email: '', password: '' };

const validationSchema = Yup.object({
  email: Yup.string().email('Invalid Email').required('Email is required'),
  password: Yup.string()
    .min(6, 'Password must be at least 6 characters')
    .required('Password is required'),
});

const Login = () => {
  const dispatch = useDispatch()
  const navigate = useNavigate()

  const handleSubmit = (values) => {
    console.log('Form Values:', values);
    dispatch(loginUserAction({ data: values }))
  };

  return (
    <>
      <Formik
        validationSchema={validationSchema}
        initialValues={initialValues}
        onSubmit={handleSubmit}
      >
        {({ isSubmitting }) => (
          <Form>
            <Grid container justifyContent="center" alignItems="center" sx={{ padding: '2rem', maxWidth: '500px', margin: '0 auto' }}>
              <Grid item xs={12} sx={{ marginBottom: '1rem' }}>
                <Field
                  as={TextField}
                  name='email'
                  placeholder='Email'
                  type='email'
                  variant='outlined'
                  fullWidth
                />
                <ErrorMessage name='email'>
                  {msg => <div className='text-red-500'>{msg}</div>}
                </ErrorMessage>
              </Grid>
              <Grid item xs={12} sx={{ marginBottom: '1rem' }}>
                <Field
                  as={TextField}
                  name='password'
                  placeholder='Password'
                  type='password'
                  variant='outlined'
                  fullWidth
                />
                <ErrorMessage name='password'>
                  {msg => <div className='text-red-500'>{msg}</div>}
                </ErrorMessage>
              </Grid>
              <Grid item xs={12}>
                <Button
                  fullWidth
                  type='submit'
                  variant='contained'
                  color='primary'
                  disabled={isSubmitting}
                  sx={{ padding: '0.8rem 0', marginBottom: '1rem' }}
                >
                  Login
                </Button>
              </Grid>
            </Grid>
          </Form>
        )}
      </Formik>
      <div className='flex justify-center -mt-3 gap-2'>
        <p className='mt-1' sx={{ textAlign: 'center', marginTop: '1rem' }}>Don't have an account?</p>
        <Button
          onClick={() => navigate('/register')}
          variant="outlined" color="primary"
        >
          Register
        </Button>
      </div>
    </>
  );
};

export default Login;
