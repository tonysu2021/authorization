import React from "react";
// import { logger } from '@core/logger'

/**
 * 錯誤邊界 (Error Boundary)
 * 1.只能捕捉子組件「渲染」、「建構子」及「各生命週期方法」中的錯誤
 * 2.牽涉到非同步執行 (ex. setTimeout, callback ) 中的錯誤無法被捕捉
 * 3.無法捕捉 event handler 中發生的錯誤
 * 
 * @author stone
 */
export function ErrorFallback() {
  return (
      <div role="alert">
        <p>Something went wrong:</p>
        {/* <pre>{error.message}</pre>
        <button onClick={resetErrorBoundary}>Try again</button> */}
      </div>
  )
}
