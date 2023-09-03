'use client'
import React, { useState, useRef } from 'react'
import { FormEventHandler } from 'react';
import { IMAGE_PATH } from '@/app/util/constants';
import Image from 'next/image';
import { ButtonComponent, LinkComponent } from '../util/button';

const Login = () => {
  const [errorMessage, setErrorMessage] = useState<string>('');
  const inputStyle: string = 'w-11/12 md:w-1/2 py-[10px] px-[20px] rounded-b-bmd shadow-xl border-neutral-700 border-opacity-30 bg-transparent border-2 border-t-0 text-black text-xl focus:outline-none focus:border-amber-500'

  const toggleForm = (event: React.MouseEvent<HTMLButtonElement, MouseEvent>) => {
    setErrorMessage('');
    
    const loginForm = document.getElementById('loginForm');
    const signupForm = document.getElementById('signupForm');
    const bigText = document.getElementsByClassName('bigText')[0];
    const smallText = document.getElementsByClassName('smallText')[0];
    if(!loginForm || !signupForm || !bigText || !smallText) return;
    loginForm.classList.toggle('opacity-0');
    loginForm.classList.toggle('z-10');
    signupForm.classList.toggle('opacity-0');
    signupForm.classList.toggle('z-10');
    bigText.textContent = bigText.textContent === 'Welcome Back!' ? 'Create an Account!' : 'Welcome Back!';
    smallText.textContent = smallText.textContent === 'Log in to your account to continue' ? 'Sign up for an account to continue' : 'Log in to your account to continue';
    document.getElementsByClassName('lowerButton')[0].textContent === 'Log In' ? document.getElementsByClassName('lowerButton')[0].textContent = 'Sign Up' : document.getElementsByClassName('lowerButton')[0].textContent = 'Log In';
  }

  const tryLogin: FormEventHandler = async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target as HTMLFormElement);
    const jsonData = JSON.stringify(Object.fromEntries(formData.entries()));

    const response = await fetch('http://localhost:8080/api/v1/auth/authenticate', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' }, 
      credentials: 'include',
      body: jsonData
    });

    if(response.ok) {
      console.log('success');
      window.location.href = '/';
    }
    else {
      document.getElementsByClassName('errorMessage')[0].classList.remove('opacity-0');
      setErrorMessage('Invalid Credentials');
    }
  }

  const showErrorMessage = (message: string) => {
    const errorElement = document.getElementsByClassName('errorMessage')[0];
    errorElement.classList.remove('opacity-0');
    setErrorMessage(message);
  }

  const trySignup: FormEventHandler = async (event) => {
    event.preventDefault();

    const formData = new FormData(event.target as HTMLFormElement);
    const email = formData.get('email');
    const username = formData.get('username');
    const password = formData.get('password');
    const confirmPassword = formData.get('confirmPassword');

    let error = '';

    if(!email) {
      error = 'Email cannot be empty';
    } else if(!username) {
      error = 'Username cannot be empty';
    } else if(!password || !confirmPassword) {
      error = 'Password cannot be empty';
    } else if(password !== confirmPassword) {
      error = 'Passwords do not match';
    }

    if(error) {
      showErrorMessage(error);
      return;
    }

    formData.delete('confirmPassword');

    const jsonData = JSON.stringify(Object.fromEntries(formData.entries()));

    const response = await fetch('http://localhost:8080/api/v1/auth/register', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' }, 
      body: jsonData
    });

    if(response.ok) {
      console.log('success');
      window.location.href = '/verify';
    }
    else {
      console.log('failure');
      document.getElementsByClassName('errorMessage')[0].classList.remove('opacity-0');
    }
  }

  return (
  <div className='w-full h-[1080px] flex justify-center items-center'>
    <div className='w-full 3xl:w-[1680px] h-full 3xl:h-[900px] flex flex-col md:flex-row 3xl:rounded-2xl 3xl:border-[1px] border-neutral-700 border-opacity-30 border-t-0'>
      <div className='loginPanel w-full md:w-1/2 h-1/3 md:h-full relative flex justify-center items-center overflow-hidden z-10 3xl:rounded-l-xl'>
        <img src={`${IMAGE_PATH}copyright/bg1.jpg`} className='absolute max-w-fit -z-10 brightness-75 '/>
        <div className='h-1/4 w-full flex flex-col justify-around items-center text-center'>
          <h1 className='bigText text-white text-3xl sm:text-6xl font-bold'>Welcome Back!</h1>
          <p className='smallText text-white text-base sm:text-2xl'>Log in to your account to continue</p>
        </div>
      </div>
      <div className='formPanel w-full md:w-3/4 h-2/3 md:h-full flex flex-col items-center justify-around relative bg-white rounded-r-xl'>
        <div className='w-full md:w-1/2 flex items-center justify-around'>
          <LinkComponent href='/' size='sm' isRounded={true} metadata={{ 
            title:'Login With Google', 
            decoration: { borderColor:'border-neutral-300', additionalStyles: 'flex justify-center items-center' }, 
            hover: { backgroundColor:'bg-neutral-300', borderColor:'border-neutral-700' }}} 
            children={
              <Image src={`${IMAGE_PATH}svg/google.svg`} width={32} height={32} alt='Google Login'/>
            }/>
          <LinkComponent href='/' size='sm' isRounded={true} metadata={{ 
            title:'Login With Microsoft', 
            decoration: { borderColor:'border-neutral-300', additionalStyles: 'flex justify-center items-center' }, 
            hover: { backgroundColor:'bg-neutral-300', borderColor:'border-neutral-700' }}} 
            children={
              <Image src={`${IMAGE_PATH}svg/microsoft.svg`} width={32} height={32} alt='Microsoft Login'/>
            }/>
          <LinkComponent href='/' size='sm' isRounded={true} metadata={{ 
            title:'Login With Apple', 
            decoration: { borderColor:'border-neutral-300', additionalStyles: 'flex justify-center items-center' }, 
            hover: { backgroundColor:'bg-neutral-300', borderColor:'border-neutral-700' }}} 
            children={
              <Image src={`${IMAGE_PATH}svg/apple.svg`} width={32} height={32} alt='Apple Login'/>
            }/>
        </div>
          <label className='errorMessage opacity-0 text-red-700 text-xl p-5'>{errorMessage}</label>
          <div className='w-full h-2/3 absolute'>
            <form action='' method='post' onSubmit={tryLogin} id='loginForm' className='w-full h-full flex flex-col items-center justify-evenly text-black transition-all duration-300 ease-in-out z-10'>
              <input type='email' name='email' id='email' placeholder='Email' className={inputStyle} />

              <input type='password' name='password' id='password' placeholder='Password' className={inputStyle} />

              <div>
                <input type='checkbox' name='remember' id='remember' />
                <label htmlFor='remember' className='ml-4'>Remember Me</label>
              </div>

              <ButtonComponent type='submit' metadata={{text: 'Log In', decoration:{}}} /> 
            </form>
          </div>
          
          <div className='w-full h-2/3 absolute'>
            <form action='' method='post' onSubmit={trySignup} id='signupForm' className='w-full h-full flex flex-col items-center justify-evenly text-black transition-all duration-300 ease-in-out opacity-0 -z-10'>
              <input type='email' name='email' id='emailSignup' placeholder='Email' className={inputStyle} />

              <input type='text' name='username' id='usernameSignup' placeholder='Username' className={inputStyle} />

              <input type='password' name='password' id='passwordSignup' placeholder='Password' className={inputStyle} />

              <input type='password' name='confirmPassword' id='confirmPassword' placeholder='Confirm Password' className={inputStyle} />

              <ButtonComponent type='submit' metadata={{text: 'Sign Up', decoration:{}}} />
            </form>
          </div>

          <ButtonComponent metadata={{ text: 'Sign Up', decoration:{ backgroundColor: 'bg-amber-500', additionalStyles: 'lowerButton z-20' } }} callback={toggleForm} />
        
      </div>
    </div>
  </div>
    )
}

export default Login;