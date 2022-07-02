import React from 'react';

const ButtonComponent = props => {
    const className = `py-2 px-4 bg-gray-700 text-cyan-50 my-4 uppercase `.concat(props.className || '');
    return (
        <button className={className} onClick={props.onClick}>
            {props.title}
        </button>
    );
};

export { ButtonComponent as Button };
