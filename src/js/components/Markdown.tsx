import React from 'react';
import ReactMarkdown from 'react-markdown'

const Markdown = props => {

    return (
        <ReactMarkdown children={props.markdown} />
    );
};

export { Markdown };
