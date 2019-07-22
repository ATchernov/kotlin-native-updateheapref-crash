//
//  Environment.swift
//  PolitStartup
//
//  Created by Andrey Tchernov on 11/04/2019.
//  Copyright © 2019 IceRock Development. All rights reserved.
//

import UIKit

class Environment {
  enum Keys: String {
    case serverBaseUrl
    
    func value() -> String {
      return (Environment.bundleDict[self.rawValue] as? String)?.replacingOccurrences(of: "\\", with: "") ?? ""
    }
  }
  
  private static let bundleDict = (Bundle.main.infoDictionary?["Environment"] as? [String: Any] ?? [:])
}
