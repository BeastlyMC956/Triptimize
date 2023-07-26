import React from 'react';
import Link from 'next/link';
import Icon from '../../../public/white_icon.png'
import Menu from '../../../public/menu_icon.png'
import styles from './header.module.css';

const linkStyle = 'h-full flex items-center hover:border-b-black hover:border-b-2';
var menuStyle = 'w-16 h-16 absolute cursor-pointer'

const menuSlide = (): React.MouseEventHandler<HTMLDivElement> | undefined => {
    menuStyle = ''

    return undefined;
}

const Header = () => {
    return (
        <header>
            <nav className='h-16 flex justify-between'>
                <div className={`${styles.container} ${styles.smallMenu} w-16 h-16 absolute cursor-pointer`} onClick={menuSlide()}>
                    <img src={Menu.src} />
                </div>
                <div className={`${styles.smallContainer} w-1/3 flex justify-evenly`}>
                    <div className='w-28 flex items-center'> 
                        <Link href="/" className='logo h-full flex items-center'>
                            <img src={Icon.src} />
                        </Link>
                    </div>
                    <div className='w-3/5 h-full flex items-center justify-evenly'>
                        <Link href="/" className={linkStyle}>Home</Link>
                        <Link href="/about" className={linkStyle}>Travel</Link>
                        <Link href="/contact" className={linkStyle}>Contact</Link>
                        <Link href="/blog" className={linkStyle}>Blog</Link>
                    </div>
                </div>
                <div className={`${styles.smallContainer} w-1/4 flex items-center justify-around`}>
                    <Link href="/signup" className={linkStyle}>Sign-Up</Link>
                </div>
                
            </nav>
        </header>
    )
}

export default Header;