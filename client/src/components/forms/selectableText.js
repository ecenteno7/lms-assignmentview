import React, { useState, useEffect, useRef } from 'react';

export const SelectableText = ({ tag, description, setSelection }) => {
  const handleMouseUp = () => {
    const selectedText = window.getSelection();
    if (selectedText.toString().length > 0) {
      const rect = selectedText.getRangeAt(0).getBoundingClientRect();
      setSelection({
        text: selectedText.toString(),
        tagID: tag,
        top: rect.top + window.scrollY,
        left: rect.left + window.scrollX,
        startIndex: description.search(selectedText.toString()),
        endIndex: description.length
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

  return (
    <div className="relative">
      <p className="text-left px-4 pb-4 break-words whitespace-pre-line">{description}</p>
    </div>
  );
};


