import Link from "next/link";
import { MouseEventHandler } from "react";

const BUTTON_STYLES = {
    sm: 'h-fit w-screen xs:w-[179px] text-center py-[10px] px-[20px] rounded-md shadow-xl',
    md: 'h-fit w-screen xs:w-[224px] text-center py-[10px] px-[20px] rounded-md shadow-xl',
    lg: 'h-fit w-screen xs:w-[269px] text-center py-[10px] px-[20px] rounded-md shadow-xl'
}

/**
 * ButtonHover Interface
 * 
 * Describes the hover properties of a button.
 * 
 * @interface
 * @property {string} backgroundColor - The background color of the button on hover.
 * @property {string} textColor - The text color of the button on hover.
 */
interface ButtonHover {
    backgroundColor: string;
    textColor: string;
}
interface ButtonMetadata {
    text: string;
    backgroundColor: string;
    textColor: string;
    hover?: ButtonHover;
}

interface LinkButtonProperties {
    size?: 'sm' | 'md' | 'lg';
    href: string;
    metadata: ButtonMetadata;
}

/**
 * ButtonProperties Interface
 * 
 * Describes the properties expected for the ButtonComponent.
 * 
 * @interface
 * @property {string} type - The type of button. Defaults to 'button'.
 * @property {string} size - The size of the button. Defaults to 'md'.
 * @property {string} text - The text of the button.
 * @property {string} backgroundColor - The background color of the button.
 * @property {string} textColor - The text color of the button.
 * @property {ButtonHover} hover - The hover properties of the button.
 * @property {React.ReactNode} children - The children (if any) of the button.
 * @property {() => void} callback - The callback function for the button.
 */
interface ButtonProperties {
    type?: 'button' | 'reset' | 'submit';
    size?: 'sm' | 'md' | 'lg';
    metadata: ButtonMetadata;
    children?: React.ReactNode;
    callback?: MouseEventHandler<HTMLButtonElement>;
}

export const ButtonComponent: React.FC<ButtonProperties> = ({ 
    type = 'button', 
    size = 'md', 
    metadata: { text, backgroundColor, textColor, hover }, 
    children, callback,
}): JSX.Element => {
    const sizeStyle = BUTTON_STYLES[size];
    const hoverStyle = hover ? `hover:${hover.backgroundColor} hover:${hover.textColor}` : '';

    if(callback) {
        const handleClick: MouseEventHandler<HTMLButtonElement> = (event) => {
            callback(event);
        }

        return (
            <button 
            type={type} 
            className={`${sizeStyle} ${backgroundColor} ${textColor} ${hoverStyle}`}
            onClick={handleClick}> {text} {children} </button>
            )
    }

    return (
        <button 
        type={type} 
        className={`${sizeStyle} ${backgroundColor} ${textColor} ${hoverStyle}`}>
            {text} {children} </button>
        )
    
}

export const LinkComponent: React.FC<LinkButtonProperties> = ({
    size = 'md',
    href,
    metadata: { text, backgroundColor, textColor, hover }
}): JSX.Element => {
    const sizeStyle = BUTTON_STYLES[size];
    const hoverStyle = hover ? `hover:${hover.backgroundColor} hover:${hover.textColor}` : '';

    return (
        <Link 
        href={href} 
        className={`${sizeStyle} ${backgroundColor} ${textColor} ${hoverStyle}`}>{text}</Link>
    )

}
