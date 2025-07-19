-- Initialize the VibeCode Studio database with sample data
-- This script runs when the PostgreSQL container starts for the first time

-- Create database (already created by POSTGRES_DB env var, but ensuring it exists)
\c vibecode_studio;

-- Grant necessary permissions
GRANT ALL PRIVILEGES ON DATABASE vibecode_studio TO vibecode;

-- Optional: Create some initial data for development
-- Note: Tables will be created automatically by JPA/Hibernate

-- Create a simple healthcheck function
CREATE OR REPLACE FUNCTION public.healthcheck() 
RETURNS text AS $$
BEGIN
    RETURN 'VibeCode Studio Database is ready!';
END;
$$ LANGUAGE plpgsql;

-- Display initialization message
SELECT 'VibeCode Studio PostgreSQL database initialized successfully!' as status;