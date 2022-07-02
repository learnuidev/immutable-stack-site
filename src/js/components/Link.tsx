import React from 'react';

const Link = props => {
    const className = `py-2 px-4 bg-gray-700 text-cyan-50 my-4 uppercase `.concat(props.className || '');
    return (
        <a className={className} href={props.href}>
            {props.title}
        </a>
    );
};

export { Link };
