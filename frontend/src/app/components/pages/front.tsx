import React from 'react';
import Link from 'next/link';
import dynamic from 'next/dynamic';
import { STYLE_BTN_DEFAULT } from '@/app/util/constants';
import Showcase from './frontShowcase';
import ImageSlideShow from './frontImageSlide';


const FrontPage = () => {
  return (
    <div>
      <div className='h-[1024px] w-full'>

        <ImageSlideShow />

        <div className='h-full flex justify-center lg:justify-normal'>
          <div className='h-full w-full lg:w-1/2 2xl:w-1/3 flex justify-center flex-col items-center'>
            
            <div id='text' className='text-white text-center h-1/3 lg:h-2/6 flex justify-between lg:justify-evenly flex-col'>
              <h2 className='text-3xl h-1/6 font-bold'>Plan Your Next Trip Today</h2>
              <p className='text-left ml-12'>From booking flights to selecting accommodations, crafting an intricate travel plan fuels your wanderlust.</p>
              <p className='text-left ml-12'>Every activity choice becomes a thread weaving unforgettable experiences together.</p>
            </div>

            <div id='buttons' className='h-1/6 w-full text-center text-black flex justify-center xl:justify-evenly items-center flex-col xl:flex-row'>
              <Link href='/' className={`${STYLE_BTN_DEFAULT} bg-amber-600 border-amber-600 text-white`}>Create a Itinerary</Link>
              <Link href='/' className={`${STYLE_BTN_DEFAULT} bg-white border-white`}>Community Itineraries</Link>
            </div>
          </div>

        </div>
      </div>
      

      <Showcase />
    </div>
  )

}

export default FrontPage;