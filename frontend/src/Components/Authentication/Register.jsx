import { TextField, Button, Grid, Radio, FormControlLabel, RadioGroup } from '@mui/material';
import { ErrorMessage, Field, Form, Formik } from 'formik';
import React, { useState } from 'react';
import { useDispatch } from 'react-redux';
import * as Yup from 'yup';
import { registerUserAction } from '../../Redux/Auth/auth.action';
import { useNavigate } from 'react-router-dom';

const initialValues = {
  firstName: '',
  lastName: '',
  email: '',
  password: '',
  gender: '',
};

const validationSchema = Yup.object({
  firstName: Yup.string().required('First Name is required'),
  lastName: Yup.string().required('Last Name is required'),
  email: Yup.string().email('Invalid Email').required('Email is required'),
  password: Yup.string()
    .min(6, 'Password must be at least 6 characters')
    .required('Password is required'),
  // gender: Yup.string().required('Please select a gender'),
});

const Register = () => {

  const [gender, setGender] = useState("")
  const dispatch = useDispatch()
  const navigate = useNavigate();

  const handleSubmit = (values) => {
    values.gender = gender
    console.log('Form Values:', values);
    dispatch(registerUserAction({ data: values }))
  };

  const handleChange = (event) => {
    setGender(event.target.value)
  }

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
                  name='firstName'
                  placeholder='First Name'
                  variant='outlined'
                  fullWidth
                />
                <ErrorMessage name='firstName'>
                  {msg => <div className='text-red-500'>{msg}</div>}
                </ErrorMessage>
              </Grid>
              <Grid item xs={12} sx={{ marginBottom: '1rem' }}>
                <Field
                  as={TextField}
                  name='lastName'
                  placeholder='Last Name'
                  variant='outlined'
                  fullWidth
                />
                <ErrorMessage name='lastName'>
                  {msg => <div className='text-red-500'>{msg}</div>}
                </ErrorMessage>
              </Grid>
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
              <Grid item xs={12} sx={{ marginBottom: '1rem' }}>
                <RadioGroup
                  onChange={handleChange}
                  row
                  aria-label="gender"
                  name="gender"
                >
                  <FormControlLabel value="male" control={<Radio />} label="Male" />
                  <FormControlLabel value="female" control={<Radio />} label="Female" />
                  <FormControlLabel
                    value="other"
                    control={<Radio />}
                    label="other"
                  />
                  <ErrorMessage name='gender'>
                    {msg => <div className='text-red-500'>{msg}</div>}
                  </ErrorMessage>
                </RadioGroup>
              </Grid>
              <Grid item xs={12} sx={{ marginBottom: '1rem' }}>
                <Button
                  fullWidth
                  type='submit'
                  variant='contained'
                  color='primary'
                  disabled={isSubmitting}
                  sx={{ padding: '0.8rem 0', marginBottom: '1rem' }}
                >
                  Signup
                </Button>
              </Grid>
            </Grid>
          </Form>
        )}
      </Formik>
      <div className='flex justify-center -mt-6 gap-2'>
        <p className='mt-1' sx={{ textAlign: 'center', marginTop: '1rem' }}>Already have an account?</p>
        <Button
          onClick={() => navigate('/login')}
          variant="outlined" color="primary"
        >
          Login
        </Button>
      </div>
    </>
  );
};

export default Register;