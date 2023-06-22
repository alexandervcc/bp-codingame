import { useState } from "react";

interface Props {
  isOpen: boolean;
  onConfirm: () => void;
  onCancel: () => void;
}

export const Modal = ({ isOpen, onCancel, onConfirm }: Props): JSX.Element => {
  return (
    <>
      {isOpen ? (
        <>
          <div>
            <h3>Desea Crear el nuevo usuario</h3>
            <button onClick={() => onCancel()}>
              <span>x</span>
            </button>
          </div>
          <div></div>
        </>
      ) : undefined}
  s  </>
  );
};
