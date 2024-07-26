import React, { useState, useEffect, useRef } from 'react';

export const SelectableText = ({ description, setSelection }) => {
  const handleMouseUp = () => {
    const selectedText = window.getSelection();
    if (selectedText.toString().length > 0) {
      const rect = selectedText.getRangeAt(0).getBoundingClientRect();
      setSelection({
        text: selectedText.toString(),
        top: rect.top + window.scrollY,
        right: rect.right + window.scrollX,
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


