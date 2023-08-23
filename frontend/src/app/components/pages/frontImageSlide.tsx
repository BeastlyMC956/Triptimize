'use client'
import React from "react"
import { useEffect } from "react"
import { IMAGE_PATH } from "@/app/util/constants"

const ImageSlideShow = () => {
  useEffect(() => {
    const images: NodeListOf<HTMLImageElement> = document.querySelectorAll('.frontImage') as NodeListOf<HTMLImageElement>;
    let index = 0;
    
    setInterval(() => {
      if(document.hidden) {
        return;
      }
    
      images[index].style.opacity = '0';
    
      index = (index + 1) % images.length;
    
      images[index].style.opacity = '1';
    }, 15 * 1000)
  }, [])
  
  const imageTransition = { transition: 'opacity 0.75s ease-in-out' };

  return (
    <div className='absolute flex justify-center items-center w-full h-[1024px] overflow-hidden -z-10'>
      <img src={`${IMAGE_PATH}copyright/bg1.jpg`} className='frontImage brightness-50 absolute opacity-1 max-w-fit' style={imageTransition}/>
      <img src={`${IMAGE_PATH}copyright/bg2.jpg`} className='frontImage brightness-50 absolute opacity-0 max-w-fit' style={imageTransition}/>
      <img src={`${IMAGE_PATH}copyright/bg3.jpg`} className='frontImage brightness-50 absolute opacity-0 max-w-fit' style={imageTransition}/>
    </div>
  )
}

export default ImageSlideShow;