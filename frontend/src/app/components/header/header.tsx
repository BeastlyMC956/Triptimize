import React from 'react';
import styles from './header.module.css';
import Menu from './menu'
import Nav from './nav'

const Header = () => {
  
  return (
    <header className={`h-20 bg-white`}>
      {/* Smaller Screens */}
      <Menu />

      {/* Wider Screen */}
      <Nav />

    </header>
    )
}

export default Header;