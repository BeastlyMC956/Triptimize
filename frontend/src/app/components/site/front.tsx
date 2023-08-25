import React from 'react';
import { LinkComponent } from '@/app/util/button';
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
              <h2 className='text-5xl h-1/6 font-bold'>Plan Your Next Trip Today</h2>
              <p className='text-left ml-12'>From booking flights to selecting accommodations, crafting an intricate travel plan fuels your wanderlust.</p>
              <p className='text-left ml-12'>Every activity choice becomes a thread weaving unforgettable experiences together.</p>
            </div>

            <div id='buttons' className='h-1/6 w-full text-center text-black flex justify-center xl:justify-evenly items-center flex-col xl:flex-row'>
              <LinkComponent size='md' href='itinerary' metadata={{text: 'Create a Itinerary', backgroundColor: 'bg-amber-600', textColor: 'text-white', hover: undefined}} />
              <LinkComponent size='md' href='/' metadata={{text: 'Community Itineraries', backgroundColor: 'bg-white', textColor: 'text-black', hover: undefined}} />
            </div>
          </div>

        </div>
      </div>
      

      <Showcase />
    </div>
  )

}

export default FrontPage;