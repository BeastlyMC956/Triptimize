import React from 'react';
import Link from 'next/link';
import { IMAGE_PATH } from '@/app/util/constants';

const navListStyle = 'p-4 m-2';

const Nav = () => {
  return (
    <nav className='hidden h-full justify-between lg:flex'>
      <div id='leftSideNav' className='flex w-4/5'>
        <div className='flex items-center justify-center w-32'>
          <Link href='/' title='Home'>
            <img src={`${IMAGE_PATH}icon.png`} className='w-12'/>
          </Link>
        </div>
        
        <div id='rightSideNav' className='flex justify-between w-1/5'>
          <ul className='h-full flex ml-5 items-center justify-evenly'>
            <li>
              <Link href='/' className={navListStyle}>Home</Link>
            </li>
            <li>
              <Link href='/' className={navListStyle}>Travel</Link>
            </li>
            <li>
              <Link href='/' className={navListStyle}>Contact</Link>
            </li>
            <li>
              <Link href='/' className={navListStyle}>Blog</Link>
            </li>
          </ul>
        </div>
      
      </div>

      <div className='flex items-center w-32'>
        <Link href='/login'>Login {'>'}</Link>
      </div>
    </nav>
  )

}

export default Nav;