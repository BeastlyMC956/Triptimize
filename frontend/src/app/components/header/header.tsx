import React from 'react';
import Link from 'next/link';
import styles from './header.module.css';
import Menu from './menu'
import Nav from './nav'

const Header = () => {
  
  return (
    <header className={`${styles.header} h-20`}>
      {/* Smaller Screens */}
      <Menu />

      {/* Wider Screen */}
      <Nav />

    </header>
    )
}

export default Header;