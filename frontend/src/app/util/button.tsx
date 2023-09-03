import Link from "next/link";
import { MouseEventHandler } from "react";

/**
 * ButtonStyles Object
 * 
 * The styles for each button size.
 * 
 * @constant
 * @property {string} sm - The styles for the small button.
 * @property {string} md - The styles for the medium button.
 * @property {string} lg - The styles for the large button.
 * @property {string} rd_sm - The styles for the small rounded button.
 * @property {string} rd_md - The styles for the medium rounded button.
 * @property {string} rd_lg - The styles for the large rounded button.
 */
const BUTTON_STYLES = {
    sm: 'h-fit w-screen xs:w-[179px] text-center py-[10px] px-[20px] rounded-md shadow-xl transition-all duration-300 ease-in-out',
    md: 'h-fit w-screen xs:w-[224px] text-center py-[10px] px-[20px] rounded-md shadow-xl transition-all duration-300 ease-in-out',
    lg: 'h-fit w-screen xs:w-[269px] text-center py-[10px] px-[20px] rounded-md shadow-xl transition-all duration-300 ease-in-out',
    rd_sm: 'h-fit w-screen xs:w-14 xs:h-14 text-center p-2 border-2 border-t-0 rounded-full shadow-2xl transition-all duration-300 ease-in-out',
    rd_md: 'h-fit w-screen xs:w-16 xs:h-16 text-center p-2 border-2 border-t-0 rounded-full shadow-2xl transition-all duration-300 ease-in-out',
    rd_lg: 'h-fit w-screen xs:w-18 xs:h-18 text-center p-2 border-2 border-t-0 rounded-full shadow-2xl transition-all duration-300 ease-in-out'
}

/**
 * ButtonDecoration Interface
 * 
 * The style information for a button.
 * 
 * @interface
 * @property {string} backgroundColor - The background color of the button.
 * @property {string} textColor - The text color of the button.
 * @property {string} borderColor - The border color of the button. 
 * @property {string} additionalStyles - Additional styles for the button.
 */
interface ButtonDecoration {
    backgroundColor?: string;
    textColor?: string;
    borderColor?: string;
    additionalStyles?: string;
}

/**
 * ButtonMetadata Interface
 * 
 * Describes the metadata expected for the ButtonComponent.
 * 
 * @interface
 * @property {string} text - The text of the button.
 * @property {string} title - The title of the button.
 * @property {ButtonDecoration} decoration - The decoration of the button.
 * @property {ButtonDecoration} hover - The hover decoration of the button.
 */
interface ButtonMetadata {
    text?: string;
    title?: string;
    decoration: ButtonDecoration;
    hover?: ButtonDecoration;
}

/**
 * LinkButtonProperties Interface
 * 
 * Describes the properties expected for the LinkComponent.
 * 
 * @interface
 * @property {string} size - The size of the button (sm, md, lg). Defaults to 'md'.
 * @property {boolean} isRounded - Whether the button is rounded or not. Defaults to false.
 * @property {string} href - The href of the link.
 * @property {ButtonMetadata} metadata - The metadata for the button.
 * @property {React.ReactNode} children - The children (if any) of the button.
 */
interface LinkButtonProperties {
    size?: 'sm' | 'md' | 'lg';
    isRounded?: boolean;
    href: string;
    metadata: ButtonMetadata;
    children?: React.ReactNode;
}

/**
 * ButtonProperties Interface
 * 
 * Describes the properties expected for the ButtonComponent.
 * 
 * @interface
 * @property {string} type - The type of button. Defaults to 'button'.
 * @property {string} size - The size of the button (sm, md, lg). Defaults to 'md'.
 * @property {boolean} isRounded - Whether the button is rounded or not. Defaults to false.
 * @property {ButtonMetadata} metadata - The metadata for the button.
 * @property {React.ReactNode} children - The children (if any) of the button.
 * @property {() => void} callback - The callback function for the button.
 */
interface ButtonProperties {
    type?: 'button' | 'reset' | 'submit';
    size?: 'sm' | 'md' | 'lg';
    isRounded?: boolean;
    metadata: ButtonMetadata;
    children?: React.ReactNode;
    callback?: MouseEventHandler<HTMLButtonElement>;
}

/**
 * Generates the style for the button based on the size and decoration.
 * 
 * @param {string} size - Size of the button (sm, md, lg)
 * @param {boolean} isRounded - Whether the button is rounded or not.
 * @param {ButtonDecoration} decoration - Decoration of the button.
 * @param {ButtonDecoration} hover - Hover decoration of the button.
 * @returns {string} The style of the button.
 */
const generateButtonStyle = (
    size: 'sm' | 'md' | 'lg',
    isRounded: boolean,
    decoration: ButtonDecoration, 
    hover?: ButtonDecoration
): string => {
    const sizeKey: 'sm' | 'md' | 'lg' | 'rd_sm' | 'rd_md' | 'rd_lg' 
    = isRounded ? `rd_${size}` : size;
    const sizeStyle = BUTTON_STYLES[sizeKey];
    const hoverStyle = hover 
    ? `hover:${hover.backgroundColor} hover:${hover.textColor} hover:${hover.borderColor}` 
    : '';
    return `${sizeStyle} ${decoration.backgroundColor} ${decoration.textColor} ${decoration.borderColor} ${hoverStyle} ${decoration.additionalStyles}`

}

/**
 * ButtonComponent
 * 
 * A Functional React Component that can be used throughout the application.
 * 
 * @param {ButtonProperties} properties - The properties for the button.
 * @returns {JSX.Element} The button component.
 */
export const ButtonComponent: React.FC<ButtonProperties> = ({
    type = 'button',
    size = 'md',
    isRounded = false,
    metadata: { text, title, decoration: { backgroundColor, textColor, borderColor, additionalStyles: extra }, hover },
    children,
    callback
}: ButtonProperties): JSX.Element => {
    const style = generateButtonStyle(size, isRounded, { backgroundColor, textColor, borderColor, additionalStyles: extra }, hover);

    return (
        <button
        type={type}
        title={title}
        onClick={callback ? (event) => callback(event) : undefined}
        className={style}> 
        {text} {children} 
    </button> 
    );
};

/**
 * LinkComponent
 * 
 * A Functional React Component that can be used throughout the application.
 * 
 * @param {LinkButtonProperties} properties - The properties for the Link.
 * @returns {JSX.Element} The Link component.
 */
export const LinkComponent: React.FC<LinkButtonProperties> = ({
    size = 'md',
    isRounded = false,
    href,
    metadata: { text, title, decoration: { backgroundColor, textColor, borderColor, additionalStyles: extra }, hover },
    children
}: LinkButtonProperties): JSX.Element => {
    const style = generateButtonStyle(size, isRounded, { backgroundColor, textColor, borderColor, additionalStyles: extra }, hover);

    return (
        <Link href={href} title={title} className={style}>
            {text} {children}
        </Link>
    );
};