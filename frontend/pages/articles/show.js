import ReactMarkdown  from 'react-markdown';

const input = '# This is a header\n\nAnd this is a paragraph'

export default ()=>{
    return (
        <ReactMarkdown source={input} />
    );
}