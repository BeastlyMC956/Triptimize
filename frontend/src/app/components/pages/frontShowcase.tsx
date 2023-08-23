import React from 'react'
import Link from 'next/link'
import { IMAGE_PATH } from '@/app/util/constants'

interface CardFeatures {
  title: string;
  icon: string;
}

class Card {
  title: string;
  description: string;
  icon: string;
  features: CardFeatures[];

  constructor(title: string, description: string, icon: string, features: CardFeatures[]) {
    this.title = title;
    this.description = description;
    this.icon = icon;
    this.features = features;
  }

  render() {
    return (
      <li className={cardStyle}>
        <Link href='/' className='w-full h-full absolute'></Link>
        <img src={`${IMAGE_PATH}${this.icon}`} alt='' className='w-32 h-32' />

        <div className={descriptionStyle}>
          <h3 className='font-semibold text-3xl'>{this.title}</h3>
          <p>{this.description}</p>
        </div>
        
        <ul className='h-1/3 w-full flex flex-col items-center justify-evenly'>
          {this.features.map((feature: CardFeatures) => {
            return (
              <li className='w-48 flex items-center text-left'>
                <img src={`${IMAGE_PATH}${feature.icon}`} className='w-12 h-12 mr-4' />
                {feature.title}
              </li>
            )
          })}
        </ul>
      </li>
    )
  }
}

const cardA = new Card('Create', 'Take your creativity to the next level', 'icon.png', [{title: 'Saving', icon: 'icons/save.png'}, {title: 'Exporting', icon: 'icons/export.png'}, {title: 'Auto Generate', icon: 'icons/ai.png'}]);
const cardB = new Card('Explore', 'See new sights and have fun', 'icon.png', [{title: 'New Places', icon: 'icons/new.png'}, {title: 'Timelines', icon: 'icons/timeline.png'}, {title: 'Notifications', icon: 'icons/notification.png'}]); 
const cardC = new Card('Engage', 'Share your Itinieraries with likeminded individuals', 'icon.png', [{title: 'Sharing', icon: 'icons/share.png'}, {title: 'Reviews', icon: 'icons/review.png'}, {title: 'Communties', icon: 'icons/community.png'}]);

const cardStyle: string = 'w-[256px] sm:w-[512px] 2xl:w-[350px] 3xl:w-[450px] h-[680px] relative flex flex-col items-center justify-evenly rounded-2xl border-[1px] border-neutral-700 border-opacity-30 border-t-0 shadow-xl hover:bg-neutral-300 hover:shadow-2xl transition-all duration-300 ease-in-out';
const descriptionStyle: string = 'h-1/4 flex flex-col justify-evenly items-center';

const Showcase = () => {
  return (
    <div className='h-[2140px] 2xl:h-[1024px] w-full flex items-center flex-col justify-evenly text-center'>
      <h2 className='font-bold text-3xl'>Solutions For Every Step of the Journey</h2>
      <div className='h-full 2xl:h-2/3 w-3/4 flex'>
        <ul className='w-full flex justify-around flex-col 2xl:flex-row items-center 2xl:items-start'>
          {cardA.render()}
          {cardB.render()}
          {cardC.render()}
        </ul>  
      </div>
    </div>
  )
}

export default Showcase;