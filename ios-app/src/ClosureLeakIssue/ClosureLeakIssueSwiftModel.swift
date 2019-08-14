//
//  NativeViewModel.swift
//  TestProj
//
//  Created by Andrey Tchernov on 14/08/2019.
//  Copyright © 2019 IceRock Development. All rights reserved.
//

import Foundation
import MultiPlatformLibrary

//Like in MPP
class ClosureLeakIssueSwiftModel: NSObject {
  private let dispatcher: NativeDispatcher<ClosureLeakIssueKotlinModelClosureInterface>
  private let itemsFactory: ClosureLeakIssueKotlinModelItemsFactory
  
  init(withDispatcher dispatcher: NativeDispatcher<ClosureLeakIssueKotlinModelClosureInterface>,
       andFactory factory: ClosureLeakIssueKotlinModelItemsFactory) {
    self.dispatcher = dispatcher
    self.itemsFactory = factory
  }
  
  func getItems() -> [AbstractItem] {
    return (1...10).map({ "Item #\($0)" }).map({ item in
      itemsFactory.createDataItem(title: item, onTap: {
        self.dispatcher.dispatchEvent({ $0.itemTapped(title: item) })
      }
    )
    })
  }
}

class NativeDispatcher<T>: EventsDispatcher where T: AnyObject {
  private weak var listener: T?
  init(withListener: T) {
    self.listener = withListener
  }
  func dispatchEvent(_ event: (T) -> Void) {
    if let nListener = listener {
      event(nListener)
    }
  }
  
  func dispatchEvent(block: @escaping (Any) -> Void) {
    if let nListener = listener {
      block(nListener)
    }
  }
}
