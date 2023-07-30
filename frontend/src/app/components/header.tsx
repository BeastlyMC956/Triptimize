import React, {useState} from 'react';
import Link from 'next/link';
import styles from './header.module.css';
import Menu from './menu'

const Header = () => {
  
  return (
    <header className={`${styles.header} h-16`}>
      {/* Smaller Screens */}
      <Menu />

      {/* Wider Screen */}
      <nav className='wideNav'>

      </nav>

    </header>
    )
}

export default Header;