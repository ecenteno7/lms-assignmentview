import React, { useState, useEffect, useRef, useContext } from 'react';
import { CourseFocusContext } from '../../context/courseFocusContext';

export const SelectableText = ({ tag, module, setSelection }) => {

  const { courseFocus } = useContext(CourseFocusContext);

  const [dynamicDescription, setDynamicDescription] = useState('');

  const handleMouseUp = () => {
    const selectedText = window.getSelection();
    if (selectedText.toString().length > 0) {
      const rect = selectedText.getRangeAt(0).getBoundingClientRect();
      setSelection({
        text: selectedText.toString(),
        tagID: courseFocus.moduleHighlight.tagID,
        top: rect.top + window.scrollY,
        left: rect.left + window.scrollX,
        startIndex: courseFocus.moduleHighlight.description.search(selectedText.toString()),
        endIndex: courseFocus.moduleHighlight.description.search(selectedText.toString()) + selectedText.toString().length
      });
    } else {
      setSelection(null);
    }
  };

  useEffect(() => {
    document.addEventListener('mouseup', handleMouseUp);
    return () => {
      document.removeEventListener('mouseup', handleMouseUp);
    };
  });

  const generateDescription = (start, end, text) => {
    const descriptionOpen = '<p>'
    const descriptionClose = '</p>'
    const highlightOpen = '<mark>'
    const highlightClosed = '</mark>'

    const jsxDescriptionString = `${descriptionOpen}${text.slice(0, start)}${highlightOpen}${text.slice(start, end)}${highlightClosed}${text.slice(end)}${descriptionClose}`

    return <div dangerouslySetInnerHTML={{ __html: jsxDescriptionString }}></div>
  }


  useEffect(() => {
    const focus = courseFocus.assignmentInsightFocus
    if (focus && focus.selection && focus.selection.tagID == module.tagID) {
      const highlightedDescription = generateDescription(focus.selection.startIndex, focus.selection.endIndex, module.description)
      setDynamicDescription(highlightedDescription);
    } else {
      setDynamicDescription(module.description)
    }
  }, [courseFocus])

  return (
    <div className="relative">
      <p className="text-left px-4 pb-4 break-words whitespace-pre-line">{dynamicDescription}</p>
    </div>
  );
};


