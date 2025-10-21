"use client";

import { useEffect, useState } from "react";

interface HealthResponse {
  status: string;
  timestamp: number;
  version: string;
}

export default function BackendStatus() {
  const [isOnline, setIsOnline] = useState<boolean | null>(null);
  const [version, setVersion] = useState<string>("");

  useEffect(() => {
    const checkHealth = async () => {
      try {
        const response = await fetch("http://localhost:8280/api/health", {
          method: "GET",
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.ok) {
          const data: HealthResponse = await response.json();
          setIsOnline(data.status === "UP");
          setVersion(data.version);
        } else {
          setIsOnline(false);
        }
      } catch {
        setIsOnline(false);
      }
    };

    // Initial check
    checkHealth();

    // Check every 30 seconds
    const interval = setInterval(checkHealth, 30000);

    return () => clearInterval(interval);
  }, []);

  if (isOnline === null) {
    return (
      <div className="flex items-center gap-2 text-xs text-muted-foreground">
        <div className="w-2 h-2 rounded-full bg-muted animate-pulse" />
        <span>Checking backend...</span>
      </div>
    );
  }

  return (
    <div className="flex items-center gap-2 text-xs">
      <div
        className={`w-2 h-2 rounded-full ${
          isOnline ? "bg-green-500" : "bg-red-500"
        } ${isOnline ? "animate-pulse" : ""}`}
      />
      <span className="text-muted-foreground">
        Backend: {isOnline ? "Online" : "Offline"}
        {isOnline && version && ` (v${version})`}
      </span>
    </div>
  );
}
