import React from 'react';

export const Modal = ({ isOpen, onClose, children }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex justify-center items-center z-50">
      <div className="bg-slate-400 p-5 rounded-xl relative">
        <button
          onClick={onClose}
          className="absolute top-2 right-2 text-2xl leading-none"
        >
          &times;
        </button>
        {children}
      </div>
    </div>
  );
};


