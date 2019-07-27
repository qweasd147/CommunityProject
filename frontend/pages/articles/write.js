import Editor from 'common/components/Editor';

const defaultMsg = "# Hello!"

export default ()=> {
  return (
    <Editor value={defaultMsg}/>
  );
}