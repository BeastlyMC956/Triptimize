'use client'
import React from "react"
import { useEffect, CSSProperties } from "react"
import Image from "next/image"
import { IMAGE_PATH } from "@/app/util/constants"

/**
 * Array of image filenames/paths
 * @type {string[]}
 */
const images: string[] = [
  'bg1.jpg',
  'bg2.jpg',
  'bg3.jpg'
]

/**
 * ImageSlideShow - React Functional Component
 * 
 * Renders a client-side slideshow of images.
 * @returns {JSX.Element} - The image slideshow.
 */
const ImageSlideShow = () => {
  useEffect(() => {
    const imageElements: NodeListOf<HTMLImageElement> = document.querySelectorAll('.frontImage') as NodeListOf<HTMLImageElement>;
    let index = 0;
    
    const slideShow = setInterval(() => {
      if(document.hidden) {
        return;
      }
    
      imageElements[index].style.opacity = '0';
    
      index = (index + 1) % imageElements.length;
    
      imageElements[index].style.opacity = '1';
    }, 15 * 1000)

    return () => { clearInterval(slideShow) }
  }, [])
  
  const imageTransition: CSSProperties = { transition: 'opacity 0.75s ease-in-out' };

  return (
    <div className='absolute flex justify-center items-center w-full h-[1024px] overflow-hidden -z-10'>
      {images.map((image: string, index: number) => (
        <Image 
        src={`${IMAGE_PATH}copyright/${image}`} alt='Background' width={2504} height={1024} 
        className={`frontImage brightness-50 absolute ${index === 0 ? 'opacity-100' : 'opacity-0'} max-w-fit`} 
        style={imageTransition} />
      ))}
      {/*<img key={`imageSlide${index}`} src={`${IMAGE_PATH}copyright/${image}`} 
        className={`frontImage brightness-50 absolute ${index === 0 ? 'opacity-100' : 'opacity-0'} max-w-fit`} 
        style={imageTransition}/> */}
    </div>
  )
}

export default ImageSlideShow;