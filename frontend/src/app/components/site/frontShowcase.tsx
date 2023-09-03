import React from 'react'
import Link from 'next/link'
import { IMAGE_PATH } from '@/app/util/constants'
import Image from 'next/image';

const CARD_STYLE: string = 'w-[256px] sm:w-[512px] 2xl:w-[350px] 3xl:w-[450px] h-[680px] relative flex flex-col items-center justify-evenly rounded-2xl border-[1px] border-neutral-700 border-opacity-30 border-t-0 shadow-xl hover:bg-neutral-300 hover:shadow-2xl transition-all duration-300 ease-in-out';
const DESCRIPTION_STYLE: string = 'h-1/4 flex flex-col justify-evenly items-center';

/**
 * CardFeatures Interface
 * 
 * Describes the individual features of a card
 * 
 * @interface
 * @property {string} title - The title of the feature
 * @property {string} icon - The icon filename/path of the feature
 */
interface CardFeatures {
  title: string;
  icon: string;
}

/**
 * CardProps Interface
 * 
 * Describes the properties expected for the CardComponent.
 * 
 * @interface
 * @property {string} title - The title of the card.
 * @property {string} description - A brief description for the card.
 * @property {string} icon - The icon filename/path for the card.
 * @property {CardFeatures[]} features - An array of features associated with the card.
 */
interface CardProps {
  title: string;
  description: string;
  icon: string;
  features: CardFeatures[];
}

/**
 * CardComponent - React Functional Component
 * 
 * Renders a card component with the given properties.
 * @param [CardProps] - The properties for the card component.
 * @returns {JSX.Element} - The card component.
 */
const CardComponent: React.FC<CardProps> = ({ title, description, icon, features }): JSX.Element => (
  <li className={CARD_STYLE}>
    <Link href='/' className='w-full h-full absolute'></Link>
    <Image src={`${IMAGE_PATH}${icon}`} width={128} height={128} alt='' className='' />

    <div className={DESCRIPTION_STYLE}>
      <h3 className='font-semibold text-3xl'>{title}</h3>
      <p>{description}</p>
    </div>
        
    <ul className='h-1/3 w-full flex flex-col items-center justify-evenly'>
      {features.map((feature: CardFeatures) => {
        return (
          <li className='w-48 flex items-center text-left'>
            <Image src={`${IMAGE_PATH}${feature.icon}`} width={48} height={48} alt={feature.title} className='w-12 h-12 mr-4' />
            {feature.title}
          </li>
        )
      })}
    </ul>
  </li>
)

const cardData = [
  {
    title: 'Create',
    description: 'Take your creativity to the next level',
    icon: 'icon.png',
    features: [
      { title: 'Saving', icon: 'icons/save.png' },
      { title: 'Exporting', icon: 'icons/export.png' },
      { title: 'Auto Generate', icon: 'icons/ai.png' }
    ]
  },
  {
    title: 'Explore',
    description: 'See new sights and have fun',
    icon: 'icon.png',
    features: [
      { title: 'New Places',  icon: 'icons/new.png' },
      { title: 'Timelines', icon: 'icons/timeline.png' },
      { title: 'Notifications', icon: 'icons/notification.png' }
    ]
  },
  {
    title: 'Engage',
    description: 'Share your Itinieraries with likeminded individuals',
    icon: 'icon.png',
    features: [
      { title: 'Sharing', icon: 'icons/share.png' },
      { title: 'Reviews', icon: 'icons/review.png' },
      { title: 'Communties', icon: 'icons/community.png' }
    ]
  }
]

/**
 * Showcase Component
 * 
 * Renders the showcase component containing multiple "cards".
 * 
 * @returns {JSX.Element} - The showcase component.
 */
const Showcase = () => {
  return (
    <div className='h-[2140px] 2xl:h-[1024px] w-full flex items-center flex-col justify-evenly text-center'>
      <h2 className='font-bold text-3xl'>Solutions For Every Step of the Journey</h2>
      <div className='h-full 2xl:h-2/3 w-3/4 flex'>
        <ul className='w-full flex justify-around flex-col 2xl:flex-row items-center 2xl:items-start'>
          {cardData.map((card: CardProps, index: number) => (
            <CardComponent key={index} {...card} />
          ))}
        </ul>
      </div>
    </div>
  )
}

export default Showcase;