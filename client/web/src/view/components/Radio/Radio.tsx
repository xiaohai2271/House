import "./Radio.less";
import React, { FC, useState } from "react";
import { CheckCircleFilled, CheckCircleOutlined } from "@ant-design/icons";

const Radio: FC<{
  style?: React.CSSProperties;
  checked: boolean;
  checkStatusChanged: (status: boolean) => void;
}> = (props) => {
  let status = props.checked;
  let checkStatusChanged = props.checkStatusChanged;
  let style = props.style;
  const [checked, setCheck] = useState(status);
  const handlerClick = () => {
    checkStatusChanged(!checked);
    setCheck(!checked);
  };
  return checked ? (
    <div style={style} className="radio" onClick={handlerClick}>
      <CheckCircleFilled style={{ fontSize: "22px", color: "#08c" }} />
    </div>
  ) : (
    <div style={style} className="radio" onClick={handlerClick}>
      <CheckCircleOutlined style={{ fontSize: "22px", color: "#a0a0a0" }} />
    </div>
  );
};

export default Radio;
