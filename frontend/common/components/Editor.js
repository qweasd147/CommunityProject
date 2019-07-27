import React, { useState, useEffect  } from 'react';
import SimpleMDE from "react-simplemde-editor";
import "easymde/dist/easymde.min.css";

export default (props)=> {
  const [value, setValue] = useState(0);
  //const [value , setValue] = useState(props);
  
  return (
    <SimpleMDE
      onChange={(textValue) => setValue(textValue)}
      value={value}
      options={{
        autofocus: true,
        spellChecker: false
      }}
    />
  );
}