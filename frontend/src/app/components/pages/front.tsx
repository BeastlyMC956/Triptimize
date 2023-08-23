import React from 'react';
import { IMAGE_PATH } from '@/app/util/constants';

const buttonStyle = 'h-fit py-[10px] px-[20px] border-2 rounded-md shadow-xl';

const FrontPage = () => {
  return (
    <div>
      <div className='h-[1024px] w-full'>
        <img src={`${IMAGE_PATH}copyright/bg.jpg`} className='brightness-50 absolute -z-10 max-w-full'/>
        <div className='h-full flex'>

          <div className='h-full w-1/3 flex justify-center flex-col items-center'>
            
            <div id='text' className='text-white text-center h-1/6 flex justify-evenly flex-col'>
              <h2 className='text-3xl h-1/6'>Plan Your Trip Today</h2>
              <p className=''>Plan Your Next Trip with Us</p>
            </div>
            <div id='buttons' className='h-2/6 w-full text-black flex justify-evenly items-center'>
              <button className={`${buttonStyle} bg-amber-600 border-amber-600 text-white`}>Create a Itinerary</button>
              <button className={`${buttonStyle} bg-white border-white`}>Community Itineraries</button>
            </div>
          </div>

        </div>
      </div>
    </div>
  )

}

export default FrontPage;