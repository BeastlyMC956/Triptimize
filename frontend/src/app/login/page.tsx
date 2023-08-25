'use client'
import React from 'react'
import { FormEventHandler } from 'react';
import { IMAGE_PATH } from '@/app/util/constants';
import Image from 'next/image';
import Link from 'next/link';
import { ButtonComponent, LinkComponent } from '../util/button';

const Login = () => {
  const inputStyle: string = 'w-1/2 py-[10px] px-[20px] rounded-b-bmd shadow-xl border-neutral-700 border-opacity-30 bg-transparent border-2 border-t-0 text-black text-xl focus:outline-none focus:border-amber-500'

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
      console.log('failure');
      document.getElementsByClassName('wrongPassword')[0].classList.remove('opacity-0');
    }
  }

  return (
  <div className='w-full h-[1080px] flex justify-center items-center'>
    <div className='w-2/3 h-5/6 flex rounded-2xl border-[1px] border-neutral-700 border-opacity-30 border-t-0'>
      <div className='loginPanel w-1/2 h-full relative flex justify-center items-center overflow-hidden z-10 rounded-l-xl'>
        <img src={`${IMAGE_PATH}copyright/bg1.jpg`} className='absolute max-w-fit -z-10 brightness-75 '/>
        <div className='h-1/4 w-full flex flex-col justify-around items-center'>
          <h1 className='text-white text-6xl font-bold'>Welcome Back!</h1>
          <p className='text-white text-2xl'>Log in to your account to continue</p>
        </div>
      </div>
      <div className='formPanel w-3/4 h-full flex flex-col items-center justify-center bg-white rounded-r-xl'>
        <div className='w-1/2 flex items-center justify-around'>
          <Link href='/' title='Login With Google' className='w-14 h-14 p-2 flex justify-center items-center rounded-full border-[1px] border-neutral-700 border-opacity-30 shadow-xl hover:bg-neutral-300 hover:shadow-2xl transition-all duration-300 ease-in-out'>
            <Image src={`${IMAGE_PATH}svg/google.svg`} width={32} height={32} alt='Google Login' /> 
          </Link>
          <Link href='/' title='Login With Microsoft' className='w-14 h-14 p-2 flex justify-center items-center rounded-full border-[1px] border-neutral-700 border-opacity-30 shadow-xl hover:bg-neutral-300 hover:shadow-2xl transition-all duration-300 ease-in-out'>
            <Image src={`${IMAGE_PATH}svg/microsoft.svg`} width={32} height={32} alt='Microsoft Login'/>
          </Link>
          <Link href='/' title='Login With Apple' className='w-14 h-14 p-2 flex justify-center items-center rounded-full border-[1px] border-neutral-700 border-opacity-30 shadow-xl hover:bg-neutral-300 hover:shadow-2xl transition-all duration-300 ease-in-out'>
            <Image src={`${IMAGE_PATH}svg/apple.svg`} width={26} height={32} alt='Apple Login' />
          </Link>
          
          
          
        </div>
        <label className='wrongPassword opacity-0 text-red-700 text-xl'>Incorrect User Details</label>
        <form action='' method='post' onSubmit={tryLogin} className='h-1/3 w-full flex flex-col items-center justify-around text-black'>
          <label htmlFor='email'>Email</label>
          <input type='email' name='email' id='email' className={inputStyle} />

          <label htmlFor='password'>Password</label>
          <input type='password' name='password' id='password' className={inputStyle} />

          <div>
            <input type='checkbox' name='remember' id='remember' />
            <label htmlFor='remember' className='ml-4'>Remember Me</label>
          </div>

          <ButtonComponent type='submit' metadata={{text: 'Log In', backgroundColor: 'bg-white', textColor: 'text-black', hover: undefined }} children={undefined} callback={undefined} />
        </form>

        <LinkComponent href='/register' metadata={{text: 'Sign Up', backgroundColor: 'bg-amber-500', textColor: 'text-black', hover: undefined }} />

      </div>
    </div>
  </div>
    )
}

export default Login;