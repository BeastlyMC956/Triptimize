import React from 'react';
import Link from 'next/link';
import Icon from '../../../public/white_icon.png'

const Nav = () => {
  return (
    <nav className='h-full'>
      <Link href='/'>
        <img id='bigMenu' src={Icon.src} />
      </Link>
    </nav>
  )

}

export default Nav;