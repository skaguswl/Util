import React, { useRef, useEffect } from "react";
import classes from "./PostCardContainerLoading.module.css";

const PostCardLoadingItem = (key) => {
  return (
    // <li className={classes['li-tag']} key={key}>
    <div key={key} className={classes.item}>
      <div className={classes[`text-area`]}> </div>
      <div className={classes[`thumbnail-area`]}> </div>
    </div>
    // </li>
  );
};

const PostCardLoadingList = (count) => {
  const loadingList = [];

  for (let i = 0; i < count; i++) {
    loadingList.push(PostCardLoadingItem(i));
  }

  return loadingList;
};

const PostCardContainerLoading = (props) => {
  const aniWrapperRef = useRef(null);

  useEffect(() => {
    setTimeout(() => {
      if (aniWrapperRef.current !== null) {
        aniWrapperRef.current.style.opacity = "255";
      }
    }, 100);
  }, [aniWrapperRef]);

  return (
    <div className={classes["ani-wrapper"]} ref={aniWrapperRef}>
      {PostCardLoadingList(props.count)}
    </div>
  );
};

export default PostCardContainerLoading;
