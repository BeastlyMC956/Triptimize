'use client'
import React, { useState } from 'react'
import Link from 'next/link';
import styles from './menu.module.css';
import Icon from '../../../public/dark_icon.png'
import MenuIcon from '../../../public/menu_icon.png'

const linkStyle = 'flex justify-center items-center w-full h-full border-b-4 border-zinc-800';

const Menu = () => {
  const [ showMenu , setShowMenu ] = useState(true);

  const handleMenu = () => {
    setShowMenu(!showMenu);
  }

  return (
    <div>
      <button type='button' onClick={handleMenu} className='w-16 h-16 absolute cursor-pointer'>
        <img id='smallMenu' src={MenuIcon.src} />
      </button>

      <div className={`${styles.slidingMenu} flex flex-col`} style={{ transform: !showMenu ? 'translateX(0)' : '' }}>
        <div className='w-full flex justify-center'>
          <img src={Icon.src} />
        </div>
        
        <nav className='w-full h-5/6 flex flex-col justify-around'>
          <Link href='/' className={linkStyle}>Home</Link>
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