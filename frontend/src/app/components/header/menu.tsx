'use client'
import React, { useState } from 'react'
import Link from 'next/link';
import styles from './menu.module.css';
import { IMAGE_PATH } from '@/app/util/constants';

const linkStyle = 'flex justify-center items-center w-full h-screen border-b-4 border-zinc-800';

const Menu = () => {
  const [ showMenu , setShowMenu ] = useState(true);

  const handleMenu = () => {
    setShowMenu(!showMenu);
  }

  return (
    <div className='lg:hidden w-full xs:w-32 h-full flex items-center justify-around xs:justify-between xs:ml-12'>
      <button type='button' onClick={handleMenu} className='w-12 h-12 cursor-pointer'>
        <img src={`${IMAGE_PATH}menu_icon.png`} alt='Sliding Menu Button' id='smallMenu' />
      </button>
      <Link href='/' className='w-12' title='Home'>
        <img src={`${IMAGE_PATH}icon.png`} alt='Triptimize Logo' className='w-12'/>
      </Link>

      <div className={`${styles.slidingMenu} flex flex-col top-0 left-0`} style={{ transform: !showMenu ? 'translateX(0)' : '' }}>
        <div className='w-full flex justify-center'>
          <img src={`${IMAGE_PATH}dark_icon.png`} alt='Triptimize Type' />
        </div>
        
        <nav className='w-full h-5/6 flex flex-col justify-around'>
          <Link href='/' onClick={handleMenu} className={linkStyle}>Home</Link>
          <Link href='/' className={linkStyle}>Travel</Link>
          <Link href='/' className={linkStyle}>Contact</Link>
          <Link href='/' className={linkStyle}>Blog</Link>
        </nav>

        <div className='w-full flex justify-center h-1/6 bg-zinc-800'>
          <button onClick={handleMenu} className='h-full w-full'>CLOSE</button>
        </div>
      </div>
    </div>
  )
}

export default Menu;